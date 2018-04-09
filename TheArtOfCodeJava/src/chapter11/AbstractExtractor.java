package chapter11;

import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractExtractor {
	private Logger logger = Logger.getLogger(AbstractExtractor.class.getName());
	
	public void extract() {
		logger.info("开始" + getExtractorName() + "。。");
		// 抽取邮件
		List<Article> articles = extractEmail();
		// 添加文章
		for (Article article : articles) {
			addArticleOrComment(article);
		}
		// 清空邮件
		cleanEmail();
		logger.info("完成" + getExtractorName() + "。。");
	}
	
	public void cleanEmail() {
		//清空邮件
	}
	
	public List<Article> extractEmail() {
		//抽取邮件
		return null;
	}
	
	public void addArticleOrComment(Article article) {
		//添加文章
	}
	
	public String getExtractorName() {
		return this.getClass().getName();
	}
}
