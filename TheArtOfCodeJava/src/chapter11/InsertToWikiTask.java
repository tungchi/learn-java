package chapter11;

public class InsertToWikiTask implements Runnable{
	private ExchangeEmailShallowDTO email;
	public InsertToWikiTask(ExchangeEmailShallowDTO email) {
		super();
		this.email = email;
	}
	
	@Override
	public void run() {
		insertToWiki(email);
	}

	void insertToWiki(ExchangeEmailShallowDTO email) {
	}
}
