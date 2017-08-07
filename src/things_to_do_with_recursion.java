
public class things_to_do_with_recursion {
	//when should we use backtracing?
	//we need to use backtracing if we want to test all the possible case and not from bottom-top
	//	but from start to end.





    //Permutations
    //Given a collection of distinct numbers, return all possible permutations
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<List<Integer>>();
        if(nums.length == 0)
            return res;

        Arrays.sort(nums);
        List<Integer> buff = new ArrayList<Integer>();
        for(int i = 0; i < nums.length; i++)
        {
            buff.add(nums[i]);
        }
        return permuting(buff);
    }
    public List<List<Integer>> permuting(List<Integer> list)
    {
        if(list.size() == 0)
        {
            List<List<Integer>> res = new ArrayList<L
            return res;ist<Integer>>();
            res.add(new ArrayList<Integer>());
        }
        else
        {
            List<List<Integer>> res = new ArrayList<List<Integer>>();
            for(int i = 0; i < list.size(); i++)
            {
                int tmp = list.get(i);
                if(i == 0 || tmp != list.get(i-1))
                {
                    list.remove(i);
                    List<List<Integer>> tmpres = permuting(list);
                    for(List<Integer> elem : tmpres)
                    {
                        elem.add(tmp);
                        res.add(elem);
                    }
                    list.add(i,tmp);
                }
            }
            return res;
        }
    }

    //permutations solution 2
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        // Arrays.sort(nums); // not necessary
        backtrack(list, new ArrayList<>(), nums);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(tempList.contains(nums[i])) continue; // element already exists, skip
                tempList.add(nums[i]);
                backtrack(list, tempList, nums);
                tempList.remove(tempList.size() - 1);
            }
        }
    }


    //permutation II (contains duplicates)
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        Arrays.sort(nums);
        backtrack(list, new ArrayList<>(), nums, new boolean[nums.length]);
        return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums, boolean [] used){
        if(tempList.size() == nums.length){
            list.add(new ArrayList<>(tempList));
        } else{
            for(int i = 0; i < nums.length; i++){
                if(used[i] || i > 0 && nums[i] == nums[i-1] && !used[i - 1]) continue;
                used[i] = true;
                tempList.add(nums[i]);
                backtrack(list, tempList, nums, used);
                used[i] = false;
                tempList.remove(tempList.size() - 1);
            }
        }
    }




}
