package test.tt;
/**
 * java两个字符串的相似度
 */
public class Levenshtein {
	  private int compare(String str, String target)
	    {
	        int d[][];              // 矩阵
	        int n = str.length();
	        int m = target.length();
	        int i;                  // 遍历str的
	        int j;                  // 遍历target的
	        char ch1;               // str的
	        char ch2;               // target的
	        int temp;               // 记录相同字符,在某个矩阵位置值的增量,不是0就是1
	        if (n == 0) { return m; }
	        if (m == 0) { return n; }
	        d = new int[n + 1][m + 1];
	        for (i = 0; i <= n; i++)
	        {                       // 初始化第一列
	            d[i][0] = i;
	        }

	        for (j = 0; j <= m; j++)
	        {                       // 初始化第一行
	            d[0][j] = j;
	        }

	        for (i = 1; i <= n; i++)
	        {                       // 遍历str
	            ch1 = str.charAt(i - 1);
	            // 去匹配target
	            for (j = 1; j <= m; j++)
	            {
	                ch2 = target.charAt(j - 1);
	                if (ch1 == ch2 || ch1 == ch2+32 || ch1+32 == ch2)
	                {
	                    temp = 0;
	                } else
	                {
	                    temp = 1;
	                }
	                // 左边+1,上边+1, 左上角+temp取最小
	                d[i][j] = min(d[i - 1][j] + 1, d[i][j - 1] + 1, d[i - 1][j - 1] + temp);
	            }
	        }
	        return d[n][m];
	    }

	    private int min(int one, int two, int three)
	    {
	        return (one = one < two ? one : two) < three ? one : three;
	    }

	    /**
	     * 获取两字符串的相似度
	     */

	    public float getSimilarityRatio(String str, String target)
	    {
	        return 1 - (float) compare(str, target) / Math.max(str.length(), target.length());
	    }

	    public static void main(String[] args)
	    {
	        Levenshtein lt = new Levenshtein();
	        String str = "1*1=1 "
	        		+ "1*2=2 2*2=4 "
	        		+ "1*3=3 2*3=6 3*3=9 "
	        		+ "1*4=4 2*4=8 3*4=12 4*4=16 "
	        		+ "1*5=5 2*5=10 3*5=15 4*5=20 5*5=25 "
	        		+ "1*6=6 2*6=12 3*6=18 4*6=24 5*6=30 6*6=36 "
	        		+ "1*7=7 2*7=14 3*7=21 4*7=28 5*7=35 6*7=42 7*7=49 "
	        		+ "1*8=8 2*8=16 3*8=24 4*8=32 5*8=40 6*8=48 7*8=56 8*8=64 "
	        		+ "1*9=9 2*9=18 3*9=27 4*9=36 5*9=45 6*9=54 7*9=63 8*9=72 9*9=81 ";
	        String target = "1*1=1 1*2=2 2*2=4 1*3=3 2*3=6 3*3=9 1*4=4 2*4=8 3*4=12 4*4=16 1*5=5 2*5=10 3*5=15 4*5=20 5*5=25 1*6=6 2*6=12 3*6=18 4*6=24 5*6=30 6*6=36 1*7=7 2*7=14 3*7=21 4*7=28 5*7=35 6*7=42 7*7=49 1*8=8 2*8=16 3*8=24 4*8=32 5*8=40 6*8=48 7*8=56 8*8=64 1*9=9 2*9=18 3*9=27 4*9=36 5*9=45 6*9=54 7*9=63 8*9=72 9*9=81 ";
	        System.out.println(str.replace("\n", "").replace(" ", ""));
	        System.out.println("similarityRatio=" + lt.getSimilarityRatio(str.replace("\n", "").replace(" ", ""), target.replace(" ", "")));
	    }
}
