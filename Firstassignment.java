class Firstassignment
{

    public boolean solve (String parent, String child)
    {
        int i=0;
        int j=0;
        while(i<parent.length() && j < child.length())
        {
            if(child.charAt(j)==parent.charAt(i))
            {
                j++;
                i++;
            }
            else{
                i++;
            }
        }

        if(j==child.length())
        {
            return true;
        }
        else{
        return false;
        }

    }
    public static void main(String args[]) {

        String parent = "Welcome Interns";
        String child = "West";

        Firstassignment obj = new Firstassignment();

        boolean ans = obj.solve(parent,child);

        System.out.println(ans);
    }
}