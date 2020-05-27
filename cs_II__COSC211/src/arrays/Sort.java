public class Sort {
	
	//Post: Elements in arr are sorted in ascending order using bubble sort
	public static void bubbleSort(int[] arr){
		for(int pass = arr.length-1; pass > 0; pass--){
			for(int i = 0; i<pass;i++){
				if(arr[i] > arr[i+1]){
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
				}
			}
		}
		System.out.println("Sorted array " + arr);
	}
	
	//Post: Elements in arr sorted in ascending order using selection sort
	public static void selectionSort(int[] arr){
		for(int pass = 0; pass < arr.length-1; pass++){
			int min = pass;
			for(int j = pass; j < arr.length; j++){
				if(arr[j] < arr[min]){
					min = j;
				}
			}
			int temp = arr[pass];
			arr[pass] = arr[min];
			arr[min] = temp;
		}
		System.out.println("Sorted array " + arr);
	}
	
	//Post: Elements in arr sorted in ascending order using insertion sort
	public static void insertionSort(int[] arr){
		for(int pass = 1; pass < arr.length; pass++){
			int pulled = arr[pass];
			int i = pass-1;
			while(i>=0){
				if(arr[i]>pulled){
					arr[i+1] = arr[i];
					i--;
				}else break;
			}
			arr[i+1] = pulled;
		}
		System.out.println("Sorted array " + arr);
	}
	
	//Post: Elements in arr sorted  in descending order using bubble sort
	public static void bubbleSortD(int[] arr){
		for(int pass = arr.length-1; pass > 0; pass--){
			for(int i = 0; i<pass;i++){
				if(arr[i] < arr[i+1]){
					int temp = arr[i];
					arr[i] = arr[i+1];
					arr[i+1] = temp;
				}
			}
		}
		System.out.println("Sorted array " + arr);
	}
	
	//Post: Elements in arr sorted in descending order using selection sort
	public static void selectionSortD(int[] arr){
		for(int pass = 0; pass < arr.length-1; pass++){
			int max = pass;
			for(int j = pass; j < arr.length; j++){
				if(arr[j] > arr[max]){
					max = j;
				}
			}
			int temp = arr[pass];
			arr[pass] = arr[max];
			arr[max] = temp;
		}
		System.out.println("Sorted array " + arr);
	}
	
	//Post: Elements in arr sorted in ascending order using insertion sort
	public static void insertionSortD(int[] arr){
		for(int pass = 1; pass < arr.length; pass++){
			int pulled = arr[pass];
			int i = pass-1;
			while(i>=0){
				if(arr[i]<pulled){
					arr[i+1] = arr[i];
					i--;
				}else break;
			}
			arr[i+1] = pulled;
		}
		System.out.println("Sorted array " + arr);
	}
	
	
	
}
