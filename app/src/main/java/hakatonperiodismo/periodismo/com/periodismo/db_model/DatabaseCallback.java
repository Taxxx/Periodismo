package hakatonperiodismo.periodismo.com.periodismo.db_model;

public interface DatabaseCallback {
	public void OnCopied();
	public void OnCreated();
	public void OnExists();
	public void OnErrorDb();
}
