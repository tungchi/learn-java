package chapter11;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuickEmailToWikiExtractor extends AbstractExtractor {
	private Logger logger = Logger.getLogger(QuickEmailToWikiExtractor.class.getName());
	private ThreadPoolExecutor threadsPool;
	private ArticleBlockingQueue<ExchangeEmailShallowDTO> emailQueue;
	private ConfluenceService confluenceService;

	public QuickEmailToWikiExtractor() {
		emailQueue = new ArticleBlockingQueue<ExchangeEmailShallowDTO>();
		int corePoolSize = Runtime.getRuntime().availableProcessors() * 2;
		threadsPool = new ThreadPoolExecutor(corePoolSize, corePoolSize, 10l, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(2000));
	}

	public void extract() {
		logger.info("开始" + getExtractorName() + "。。");
		long start = System.currentTimeMillis();
		// 抽取所有邮件放到队列里
		new ExtractEmailTask().start();
		// 把队列里的文章插入到Wiki
		insertToWiki();
		long end = System.currentTimeMillis();
		double cost = (end - start) / 1000;
		logger.info("完成" + getExtractorName() + ",花费时间：" + cost + "秒");
	}

	/**
	 * 把队列里的文章插入到Wiki
	 */
	private void insertToWiki() {
		// 登录Wiki,每间隔一段时间需要登录一次
		confluenceService.login(RuleFactory.USER_NAME, RuleFactory.PASSWORD);
		try {
			while (true) {
				// 2秒内取不到就退出
				ExchangeEmailShallowDTO email = emailQueue.poll(2, TimeUnit.SECONDS);
				if (email == null) {
					break;
				}
				threadsPool.submit(new InsertToWikiTask(email));
			} 
		} catch (Exception e) {
			logger.log(Level.ALL, e.getMessage(), e);
		}
	}

	public List<Article> extractEmail() {
		List<ExchangeEmailShallowDTO> allEmails = getEmailService().queryAllEmails();
		if (allEmails == null) {
			return null;
		}
		for (ExchangeEmailShallowDTO exchangeEmailShallowDTO : allEmails) {
			emailQueue.offer(exchangeEmailShallowDTO);
		}
		return null;
	}
	
	public EmailService getEmailService() {
		return null;
	}

	/**
	 * 抽取邮件任务
	 *
	 * @author tengfei.fangtf
	 */
	public class ExtractEmailTask extends Thread {
		public void run() {
			extractEmail();
		}
	}
}