package theo.constant;

public enum YesOrNo {
	
	YES(true,"Yes/��"),
	NO(false,"No/��");
	
	private boolean cmd;
	private String msg;
	YesOrNo(boolean cmd,String msg){
		this.cmd=cmd;
		this.msg=msg;
	}
	
	public boolean getCmd() {
		return cmd;
	}
	public String getMsg() {
		return msg;
	}
}
