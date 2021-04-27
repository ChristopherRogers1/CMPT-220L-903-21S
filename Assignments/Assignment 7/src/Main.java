public class Main
{
    public static void main(String args[])
    {
        int[] list = {5, 3, 1, 10, 6};
        int size = list.length;
        for (int x = 0; x < size-1; x++)
        {
            for (int y = 0; y < size-1; y++ )
            {
                if (list[y] > list[y+1])
                {
                    int placehold = list[y];
                    list[y] = list[y+1];
                    list[y+1] = placehold;
                }
            }
        }
        for (int z = 0; z < size; z++)
        {
            System.out.print (list[z] + " ");
        }
    }
}

