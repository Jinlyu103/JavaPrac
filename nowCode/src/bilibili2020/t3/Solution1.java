package bilibili2020.t3;

public class Solution1 {
    public String print_diamond (int n) {
        if (n%2 == 0)
            return "";
        String[][] matrix = new String[n][n];
        for (int i = 0; i<n; i++){
            for (int j = 0; j<n; j++){
                matrix[i][j] = "*";
            }
        }

        //只考虑左上角
        int mid = n/2;
        for (int j=0; j < mid+1; j++){
            matrix[mid][j] = Integer.toString(j+1);
        }
        for (int i = 0; i<mid+1; i++){
            matrix[i][mid] = Integer.toString(i+1);
        }

        for (int i = 0; i<mid; i++){
            for (int j = mid; j>mid-i-1; j--){
                int t = i+1-mid+j;
                matrix[i][j] = Integer.toString(t);
            }
        }

        for (int i = 0; i<mid+1; i++){
            for (int j = mid+1; j<n; j++){
                matrix[i][j] = matrix[i][n-1-j];
            }
        }

        for (int i = mid+1; i<n; i++){
            for (int j = 0; j<n; j++){
                matrix[i][j] = matrix[n-1-i][j];
            }
        }

        StringBuffer res = new StringBuffer();
        for (int i = 0; i<n; i++){
            StringBuffer row = new StringBuffer();
            for (int j = 0; j<matrix[i].length; j++){
                row.append(matrix[i][j]);
            }
            if (i!=n-1){
                res.append(row.toString() + '|');
            } else {
                res.append(row.toString());
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        int n = 11;
        System.out.println(sol.print_diamond(n));
    }
}
