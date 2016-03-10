import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Test {

	public static void main(String[] args) {
		/*String[] str = "rohit.lohiya-1988".split("\\.|-");
		
		for(int i = 0; i < str.length ;i++)
		{
			System.out.println(str[i]);
		}*/
		
		
		List<String> associated = new ArrayList<String>();
		List<String> all = new ArrayList<String>();
		List<String> addded = null ;
		List<String> removed = null ;
		
		associated.addAll(Arrays.asList("a","b","c","d"));
		all.addAll(Arrays.asList("a","b","p","q"));
		
		
		
		System.out.println("associated ==>"+associated);
		System.out.println("all ==>"+all);
		System.out.println("addded ==>"+addded);
		System.out.println("removed ==>"+removed);
		
		System.out.println("--------------");
		
		addded = new ArrayList<String>(associated);		
		addded.removeAll(null);
		
		System.out.println("associated ==>"+associated);
		System.out.println("all ==>"+all);
		System.out.println("addded ==>"+addded);
		System.out.println("removed ==>"+removed);
		
		System.out.println("--------------");
		
		removed = new ArrayList<String>(all);
		removed.removeAll(associated);
		
		System.out.println("associated ==>"+associated);
		System.out.println("all ==>"+all);
		System.out.println("addded ==>"+addded);
		System.out.println("removed ==>"+removed);
		
		
	}

}
