package problem0011;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author micmeyer
 */
public class Problem0011
{
    public static void main(String[] args)
    {
        GridProductFinder gpf = new GridProductFinder(Data.DATA, 4);
        
//        List<Long> allProducts = new LinkedList<>();
//        
//        allProducts.addAll(gpf.findHorizontals());
//        allProducts.addAll(gpf.findVerticals());
//        allProducts.addAll(gpf.findDiagonalsAigu());
//        allProducts.addAll(gpf.findDiagonalsGrave());
//        
//        allProducts.stream().mapToLong(Long::longValue).max().ifPresent(max -> {System.out.println(max); });
        
        gpf.findHorizontals();
        gpf.findVerticals();
        gpf.findDiagonalsAigu();
        gpf.findDiagonalsGrave();
        
        System.out.println(gpf.getMaxProduct());
        System.out.println(gpf.getMaxProductIdentifier());
    }
}
