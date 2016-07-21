import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RecursiveAction;

public class QuickSortThread<T extends Comparable<T>> extends RecursiveAction{

	private List<T> List;
	public ExecutorService Exec;

	public QuickSortThread() {
		
	}

	public QuickSortThread(List<T> list){
		this.List = list;
	}
	
	public QuickSortThread(List<T> list,ExecutorService exec){
		this.List = list;
		this.Exec = exec;
		
	}
	
	public void print(){
		for(T elem : List)
		{
			System.out.print(elem+",");
		}
		System.out.println();
	}
	
	@Override
	protected void compute()
	{
		if(List.size() <= 1)return;
		int left = 0;
		int right = List.size() -1;
		T pivot = List.get(left);
		

		while(left <= right)
		{
			while(pivot.compareTo(List.get(left)) < 0)left++;
			while(pivot.compareTo(List.get(right)) > 0)right--;
			if(left <= right){
				T temp = List.get(left);
				List.set(left, List.get(right));
				List.set(right, temp);
				left++;
				right--;
			}
		}
		QuickSortThread<T> qs1 = new QuickSortThread<T>(List.subList(0, right+1),Exec);
		QuickSortThread<T> qs2 = new QuickSortThread<T>(List.subList(left,List.size()),Exec);
		qs1.compute();
		qs2.fork();
		qs2.join();
	}
}