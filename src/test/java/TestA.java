import java.util.List;
import java.util.Vector;

/**
 * Created by yezhangyuan on 2018-05-21.
 *
 * @author yezhangyuan
 */
public class TestA {

	public static void main(String[] args){
//		Integer[] nums = new Integer[]{23,56,89,89,89,45,9,78,91};
//		List<Integer> ints = Arrays.asList(nums);
//		System.out.println(ints.size());
//
//		TreeSet<Integer> ts = new TreeSet<Integer>(ints);
//
//		System.out.println(ts);
//		System.out.println(ts.last());
//		System.out.println(ts.lower(ts.last()));
//		System.out.println(ts.lower(ts.lower(ts.last())));
//
//		System.out.println("=========================");
//		//固定列表
//		List<Integer> initData = Collections.nCopies(100,0);
//		//转化可变列表
//		List<Integer> data = new ArrayList<>(initData);
//		System.out.println(data);
//		Iterator<Integer> iterator = data.iterator();

//		final List<String> tickets = new ArrayList<>();
//		for(int i = 0;i < 10000;i++){
//			tickets.add("火车票" + i);
//		}
//		for(int i = 0;i < 10;i++){
//			new Thread(){
//				@Override
//				public void run() {
//					while(true){
//						System.out.println(Thread.currentThread().getId() + "--" + tickets.remove(0));
//					}
//				}
//			}.start();
//		}

		final List<String> tickets = new Vector<>();
		for(int i = 0;i < 10000;i++){
			tickets.add("火车票" + i);
		}

		for(int i = 0;i < 10;i++){
			new Thread(){
				@Override
				public void run() {
					while(true){
						System.out.println(Thread.currentThread().getId() + "--" + tickets.remove(0));
					}
				}
			}.start();
		}
		System.out.println(tickets.size());

//		new Thread(){
//			public void run(){
//				while(true){
////					tickets.removeAll(tickets.subList(0,1000));
////				System.out.println(tickets.size());
//					System.out.println(Thread.currentThread().getId() + "--" + tickets.remove(0));
//				}
//			}
//		}.start();
//
//		System.out.println("============================================" + tickets.size());

//		System.out.println(dataList.size());

	}
}
