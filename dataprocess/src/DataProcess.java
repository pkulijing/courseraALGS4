public class DataProcess 
{
	public static void main(String[] args)
	{
		In in = new In("data.txt");
		Out out = new Out("processedData.txt");
		while(!in.isEmpty())
		{
			int num = in.readInt();
			if (num > 668)
			{
				int num1 = in.readInt();
				int num2 = in.readInt();
				out.printf("%d\t%d\t%d\n", num,num1,num2);
			}
			else if (num > 543)
			{
				in.readInt();
				in.readInt();
				int num1 = in.readInt();
				int num2 = in.readInt();
				out.printf("%d\t%d\t%d\n", num,num1,num2);
			}
			else break;
		}
	}
}
