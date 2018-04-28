 //删除重复的元素，使用set唯一性
 public static ArrayList<People> removeDuplicteUsers(ArrayList<People> userList) {
        Set<People> s = new TreeSet<People>(new Comparator<People>() {

            @Override
            public int compare(People o1, People o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        s.addAll(userList);
        return new ArrayList<People>(s);
    }
 
