package theo.constant;

public enum YesOrNo {
	
	YES(true,"Yes/ÊÇ"),
	NO(false,"No/²»");
	
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
