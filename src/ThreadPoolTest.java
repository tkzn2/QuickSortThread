import java.util.Random;
import java.util.Vector;
import java.util.concurrent.ForkJoinPool;

public class ThreadPoolTest {

	public final static int THREAD_NUM = 10;
	public static void main(String[] args) {

		Vector<Integer> list = new Vector<>();
		Vector<Integer> list2;
		Random rnd = new Random();
		long start;
		long end;
		QuickSortThread<Integer> qst;
		QuickSort<Integer> qs;

		for(int i = 0;i < 100;i++)
		{
			list.add(rnd.nextInt(100));
		}

		list2 = new Vector<>(list);

		qst = new QuickSortThread<>(list);
		qs = new QuickSort<>(list2);

		ForkJoinPool fjp = new ForkJoinPool();

		start = System.nanoTime();

		fjp.invoke(qst);

		end = System.nanoTime();
		System.out.println("Time:" + (end - start) / 1000000f + "ms");

		fjp.shutdown();

		qst.print();

		start = System.nanoTime();

		qs.sort();

		end = System.nanoTime();

		System.out.println("Time:" + (end - start) / 1000000f + "ms");

		qs.print();
	}
}

