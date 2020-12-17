import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ArrayList;


/**
 * LeetCode 15.
 * 3Sum https://leetcode.com/problems/3sum/
 * 
 * FIRST experiment with the following Easy problems:
 * 
 *   1 Two Sum
 * 167 Two Sum II - Input array is sorted
 * 653 Two Sum IV - Input is a BST
 */
public class ThreeSum {


    /**
     * Find all unique triplets in the array which gives the sum of zero.
     * Brute force approach O(n^3).
     * 
     * Time Limit Exceeded
     */
    static List<List<Integer>> threeSum2(int[] nums) {

        // **** initialization ****
        List<List<Integer>> ll  = new ArrayList<List<Integer>>();
        int[] arr               = new int[3];

        // **** loop for first element ****
        for (int i = 0; i < nums.length - 2; i++) {

            // **** loop for second element ****
            for (int j = i + 1; j < nums.length - 1; j++) {

                // **** loop for third element ****
                for (int k = j + 1; k < nums.length; k++) {

                    // **** compute sum ****
                    int sum = nums[i] + nums[j] + nums[k];

                    // **** consider this set ****
                    if (sum == 0) {

                        // **** populate and sort array ****
                        arr[0] = nums[i];
                        arr[1] = nums[j];
                        arr[2] = nums[k];
                        Arrays.sort(arr);

                        // **** allocate and populate list with array ****
                        ArrayList<Integer> lst = new ArrayList<Integer>();
                        lst.add(arr[0]);
                        lst.add(arr[1]);
                        lst.add(arr[2]);

                        // **** add this list to the list of lists ****
                        if (!ll.contains(lst))
                            ll.add(lst);
                    }
                }
            }
        }

        // **** return result ****
        return ll;
    }


    /**
     * Find all unique triplets in the array which gives the sum of zero.
     * O(n ^ 2)
     * 
     * Time Limit Exceeded
     */
    static List<List<Integer>> threeSum1(int[] nums) {

        // **** initialization ****
        List<List<Integer>> ll  = new ArrayList<List<Integer>>();
        HashSet<Integer> s      = new HashSet<Integer>();
        int[] arr               = new int[3];

        // **** ****
        for (int i = 0; i < nums.length - 1; i++) {

            // **** clear the hash set ****
            s.clear();

            // **** find all pairs with sum equals to: -nums[i] ****
            for (int j = i + 1; j < nums.length; j++) {

                // **** ****
                int x = -(nums[i] + nums[j]);

                // **** ****
                if (s.contains(x)) {

                    // **** populate and sort array (to avoid duplicates) ****
                    arr[0] = x;
                    arr[1] = nums[i];
                    arr[2] = nums[j];                 
                    Arrays.sort(arr);

                    // **** allocate and populate list with array ****
                    ArrayList<Integer> lst = new ArrayList<Integer>();
                    lst.add(arr[0]);
                    lst.add(arr[1]);
                    lst.add(arr[2]);

                    // **** add this list to the list of lists ****
                    if (!ll.contains(lst))
                        ll.add(lst);
                } else {

                    // **** ****
                    s.add(nums[j]);
                }
            }
        }

        // **** return result ****
        return ll;
    }


    /**
     * Find all unique triplets in the array which gives the sum of zero.
     * O(n ^ 2)
     * 
     * Runtime: 1809 ms, faster than 5.01% of Java online submissions.
     * Memory Usage: 43.7 MB, less than 25.47% of Java online submissions.
     */
    static List<List<Integer>> threeSum0(int[] nums) {

        // **** initialization ****
        List<List<Integer>> ll  = new ArrayList<List<Integer>>();
        int[] arr               = new int[3];

        // **** sort nums array ****
        Arrays.sort(nums);

        // **** ****
        for (int i = 0; i < nums.length - 1; i++) {

            // **** ****
            int l = i + 1;
            int r = nums.length - 1;
            int x = nums[i];

            // **** ****
            while (l < r) {

                // **** ****
                if (x + nums[l] + nums[r] == 0) { 

                    // **** populate and sort array (avoid duplicates) ****
                    arr[0] = x;
                    arr[1] = nums[l];
                    arr[2] = nums[r];

                    // **** clear and populate list with array ****
                    ArrayList<Integer> lst  = new ArrayList<Integer>();
                    lst.add(arr[0]);
                    lst.add(arr[1]);
                    lst.add(arr[2]);

                    // **** add this list to the list of lists ****
                    if (!ll.contains(lst))
                        ll.add(lst);

                    // **** update indices ****
                    l++;
                    r--;
                }

                // **** ****
                else if (x + nums[l] + nums[r] < 0) {
                    l++;
                }

                // **** ****
                else 
                    r--;
            }
        }

        // **** return result ****
        return ll;
    }


    /**
     * Find all unique triplets in the array which gives the sum of zero.
     *
     * Runtime: 19 ms, faster than 78.26% of Java online submissions.
     * Memory Usage: 43.3 MB, less than 37.78% of Java online submissions.
     */
    static List<List<Integer>> threeSum(int[] nums) {

        // **** initialization ****
        List<List<Integer>> ll  = new LinkedList<>();

        // **** sort nums array (allows indices to move low -> <- high) ****
        Arrays.sort(nums);

        // **** traverse nums array (left to right) ****
        for (int i = 0; i < nums.length - 2; i++) {

            // **** skip duplicates ****
            if (i == 0 || (i > 0 && nums[i] != nums[i - 1])) {

                // **** set boundaries ****
                int low     = i + 1;
                int high    = nums.length - 1;
                int sum     = 0 - nums[i];

                // **** traverse rest of pairs (low -> <- high) ****
                while (low < high) {

                    // **** check if we found a pair that matches the sum ****
                    if (nums[low] + nums[high] == sum) {
                        ll.add(Arrays.asList(nums[i], nums[low], nums[high]));

                        // **** skip duplicates ****
                        while (low < high && nums[low] == nums[low + 1])
                            low++;

                        while (low < high && nums[high - 1] == nums[high])
                            high--;

                        // **** ****
                        low++;
                        high--;
                    } 
                    
                    // **** reduce high ****
                    else if (nums[low] + nums[high] > sum) {
                        high--;
                    }

                    // **** increase low ****
                    else {
                        low++;
                    }
                }
            }
        }

        // **** return result ****
        return ll;
    }


    /**
     * Test scaffolding
     * 
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {

        // **** open buffered reader ****
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // **** read and split input line ****
        String[] strArr = br.readLine().trim().split(",");

        // **** close buffered reader ****
        br.close();

        // **** create and initialize array of integer ****
        int[] arr = Arrays.stream(strArr).mapToInt(Integer::parseInt).toArray();

        // ???? ????
        System.out.println("main <<<       arr: " + Arrays.toString(arr));


        // **** call function to generate results ****
        List<List<Integer>> ll = threeSum2(arr);

        // **** display results ****
        System.out.println("main <<< threeSum2: " + ll.toString());


        // **** call function to generate results ****
        ll = threeSum1(arr);

        // **** display results ****
        System.out.println("main <<< threeSum1: " + ll.toString());


        // **** call function to generate results ****
        ll = threeSum0(arr);

        // **** display results ****
        System.out.println("main <<< threeSum0: " + ll.toString());


        // **** call function to generate results ****
        ll = threeSum(arr);

        // **** display results ****
        System.out.println("main <<<  threeSum: " + ll.toString());
    }
}