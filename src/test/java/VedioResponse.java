/**
 * Created by yezhangyuan on 2018-10-26.
 *
 * @author yezhangyuan
 */
public class VedioResponse {
	private String cmd;
	private String resp;
	private String msg;
	private Params params;
	private String v;

	public String getCmd() {
		return cmd;
	}

	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public String getResp() {
		return resp;
	}

	public void setResp(String resp) {
		this.resp = resp;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Params getParams() {
		return params;
	}

	public void setParams(Params params) {
		this.params = params;
	}

	public String getV() {
		return v;
	}

	public void setV(String v) {
		this.v = v;
	}

	public static class Params{

		private String vedio;

		public String getVedio() {
			return vedio;
		}

		public void setVedio(String vedio) {
			this.vedio = vedio;
		}
	}

}
