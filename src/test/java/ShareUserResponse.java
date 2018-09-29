/**
 * Created by yezhangyuan on 2018-09-27.
 *
 * @author yezhangyuan
 */
public class ShareUserResponse {

	private String resp;
	private String msg;
	private Params params;

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

	public static class Params{

		private String sn;

		public String getSn() {
			return sn;
		}

		public void setSn(String sn) {
			this.sn = sn;
		}
	}
}
