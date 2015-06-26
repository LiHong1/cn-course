package cm.dto;

/**
 * Created by li hong on 2015/5/13.
 */
public class SystemDto {
	//能否登录
	public Boolean loginAble;
	//能否换机器
	public Boolean replaceMachineAble;
	//作业区大小
	public int jobArea;
	//学期开始时间
	public String startTermTime;
	//上传文件根目录
	public String fileRoot;

	public Boolean getLoginAble() {
		return loginAble;
	}

	public void setLoginAble(Boolean loginAble) {
		this.loginAble = loginAble;
	}

	public Boolean getReplaceMachineAble() {
		return replaceMachineAble;
	}

	public void setReplaceMachineAble(Boolean replaceMachineAble) {
		this.replaceMachineAble = replaceMachineAble;
	}

	public int getJobArea() {
		return jobArea;
	}

	public void setJobArea(int jobArea) {
		this.jobArea = jobArea;
	}

	public String getStartTermTime() {
		return startTermTime;
	}

	public void setStartTermTime(String startTermTime) {
		this.startTermTime = startTermTime;
	}

	public String getFileRoot(){
		return this.fileRoot;
	}

	public void setFileRoot(String fileRoot){
		this.fileRoot = fileRoot;
	}
}
