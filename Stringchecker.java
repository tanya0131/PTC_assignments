import java.util.*;

public class Stringchecker {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Enter the parent string: ");
            String parent = scanner.nextLine();

            System.out.print("Enter the child string: ");
            String child = scanner.nextLine();

            if (parent == null || child == null) {
                throw new NullPointerException("Input strings cannot be null");
            }

            

            if (isSubsequence(parent, child)) {
                System.out.println("Output: true");
                printCharacterStatistics(parent,child);
            }
            else if(isSubsequence(parent, new StringBuilder(child).reverse().toString())) 
            {
                System.out.println("Output: true");
                printCharacterStatistics(parent,new StringBuilder(child).reverse().toString());
            }
            else {
                System.out.println("Output: false");
                printCharacterStatistics(parent,child);
            }

        } catch (NullPointerException e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }

    private static boolean isSubsequence(String parent, String child) {

        int parentIndex = 0;
        for (int i = 0; i < child.length(); i++) {
            char c = child.charAt(i);
            while (parentIndex < parent.length()) {
                if (c == parent.charAt(parentIndex)) {
                    
                    break;
                }
                parentIndex++;
            }
            if (parentIndex == parent.length()) {

                return false;
            }
            parentIndex++;
        }
        return true;
    }

    private static void printCharacterStatistics(String parent,String child) {

        Map<Character, Integer> foundFreq = new HashMap<>();
        Map<Character, Integer> missedFreq = new HashMap<>();

        int parentIndex = 0;
        for (int i = 0; i < child.length(); i++) {
            char c = child.charAt(i);
            while (parentIndex < parent.length()) {
                if (c == parent.charAt(parentIndex)) {
                    foundFreq.put(c, foundFreq.getOrDefault(c, 0) + 1);
                    break;
                }
                parentIndex++;
            }
            if (parentIndex == parent.length()) {
                missedFreq.put(c, missedFreq.getOrDefault(c, 0) + 1);
                
            }
            parentIndex++;
        }


        System.out.println("Characters Found:");
        for (Map.Entry<Character, Integer> entry : foundFreq.entrySet()) {
            System.out.println(entry.getKey() + ": Freq = " + entry.getValue());
        }

        if (missedFreq.isEmpty()) {
            System.out.println("Characters Missed: None");
        } else {
            System.out.println("Characters Missed:");
            for (Map.Entry<Character, Integer> entry : missedFreq.entrySet()) {
                System.out.println(entry.getKey() + ": Freq = " + entry.getValue());
            }
        }
    }
}
