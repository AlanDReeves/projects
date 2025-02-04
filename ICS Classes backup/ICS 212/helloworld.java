public class helloworld
{
  public static void main(String[] args)
  {
    int num;
    int i;

    for (i = 0; i < 3; i = i + 1)
    {
      if (i < 1)
        System.out.println("Hello");
      else if (i < 2)
        System.out.println("World");
      else
        System.out.println("!!!");
    }

    num = 0;
    while (num < 4)
    {
      System.out.println("While loop!");
      num = num + 1;
    }

    num = 0;
    do
    {
      System.out.println("Do-while loop!");
      num = num + 1;
    }
    while (num < 3);
  }
}
