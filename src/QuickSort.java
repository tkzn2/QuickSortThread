import java.util.List;

public class QuickSort<T extends Comparable<T>>{

	private List<T> vec;

	public QuickSort(){

	}

	public QuickSort(List<T> vec){
		this.vec = vec;
	}
	
	public void print(){
		for(T elem : vec)
		{
			System.out.print(elem+",");
		}
	}

	public void sort(){
		quickSort(0,vec.size()-1);
	}
	
	private void quickSort(int left,int right)
	{
		if(left >= right)return;
		int rInd = right;
		int lInd = left;
		T pivot = vec.get((right + left)/2);

		while(rInd >= lInd)
		{
			while(pivot.compareTo(vec.get(lInd)) < 0)lInd++;
			while(pivot.compareTo(vec.get(rInd)) > 0)rInd--;
			if(lInd<=rInd){
				T temp = vec.get(lInd);
				vec.set(lInd, vec.get(rInd));
				vec.set(rInd, temp);
				lInd++;
				rInd--;
			}
		}

		quickSort(left,rInd);
		quickSort(lInd, right);
	}
}