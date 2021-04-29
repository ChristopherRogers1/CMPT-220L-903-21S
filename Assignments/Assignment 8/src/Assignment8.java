public class Assignment8
{
    //Head Recursion
    public static int headfibonacci(int oldnum, int newnum)
    {
        int placeholder;
        if (newnum < 200)
        {
            placeholder = newnum;
            newnum = oldnum + newnum;
            oldnum = placeholder;

            headfibonacci(oldnum, newnum);

            System.out.println(oldnum);
            return 0;
        }
        else
            return newnum;
    }

    //Tail Recursion
    public static int tailfibonacci(int oldnum, int newnum)
    {
        int placeholder;

        if (newnum < 200)
        {
            System.out.println(newnum);

            placeholder = newnum;
            newnum = oldnum + newnum;
            oldnum = placeholder;

            return (tailfibonacci(oldnum, newnum));
        }
        else
        {
            return newnum;
        }
    }


    public static void main(String args[])
    {
        System.out.println("Head Recursion: ");
        headfibonacci(0,1);
        System.out.println(" ------------------------- ");
        System.out.println("Tail Recursion: ");
        tailfibonacci(0,1);
    }
}
