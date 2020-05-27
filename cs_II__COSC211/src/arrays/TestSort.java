import java.util.Arrays;

public class TestSort {
	
	
	public static void main(String[] args){
		
		//Test of bubbleSort
		int[] arr1 = {3,4,6,2,4};
		System.out.println("Array before bubble sort: " + Arrays.toString(arr1));
		Sort.bubbleSort(arr1);
		System.out.println("Array after bubble sort: " + Arrays.toString(arr1) + "\n");
		
		//Test of selectionSort
		int[] arr2 = {3,4,6,2,4};
		System.out.println("Array before selection sort: " + Arrays.toString(arr2));
		Sort.selectionSort(arr2);
		System.out.println("Array after selection sort: " + Arrays.toString(arr2) + "\n");
		
		//Test of insertionSort
		int[] arr3 = {3,4,6,2,4};
		System.out.println("Array before insertion sort: " + Arrays.toString(arr3));
		Sort.insertionSort(arr3);
		System.out.println("Array after insertion sort: " + Arrays.toString(arr3) + "\n");
		
		//Test of bubbleSortD
		int[] arr4 = {3,4,6,2,4};
		System.out.println("Array before descending bubble sort: " + Arrays.toString(arr4));
		Sort.bubbleSortD(arr4);
		System.out.println("Array after descending bubble sort: " + Arrays.toString(arr4) + "\n");
		
		//Test of selectionSortD
		int[] arr5 = {3,4,6,2,4};
		System.out.println("Array before descending selection sort: " + Arrays.toString(arr5));
		Sort.selectionSortD(arr5);
		System.out.println("Array after descending selection sort: " + Arrays.toString(arr5) + "\n");
		
		//Test of insertionSortD
		int[] arr6 = {3,4,6,2,4};
		System.out.println("Array before descending insertion sort: " + Arrays.toString(arr6));
		Sort.insertionSortD(arr6);
		System.out.println("Array after descending insertion sort: " + Arrays.toString(arr6) + "\n");
		
	}
}
