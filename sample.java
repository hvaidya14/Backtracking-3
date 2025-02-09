class Solution {
    List<List<String>> result = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        
        boolean[][] grid = new boolean[n][n];
        backtrack(grid, n, 0);
        return result;
    }
    private void backtrack(boolean[][] grid, int n, int row) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (int i=0;i<n;i++) {
                StringBuilder s = new StringBuilder();
                for (int j=0;j<n;j++) {
                    if (grid[i][j] == false) {
                        s.append('.');
                    } else {
                        s.append('Q');
                    }
                }
                list.add(s.toString());
            }
            result.add(list);
            return;
        }
        for (int col=0;col<n;col++) {
            if(issafe(grid, row, col, n)) {
                grid[row][col] = true;
                backtrack(grid, n, row+1);
                grid[row][col] = false;
            }
        }
    }
    private boolean issafe(boolean[][] grid, int row, int col, int n) {
        int i=row, j=col;
        while (i >=0) {
            if (grid[i][col] == true) {
                return false;
            }
            i--;
        }
        while (j>=0) {
            if (grid[row][j] == true) {
                return false;
            }
            j--;
        }
        i=row;
        j=col;
        while (i>=0 && j >=0) {
            if (grid[i][j] == true) {
                return false;
            }
            i--;
            j--;
        }
        i=row;
        j=col;
        while (i>=0 && j <n) {
            if (grid[i][j] == true) {
                return false;
            }
            i--;
            j++;
        }
        return true;
    }
}


class Solution {
    public boolean exist(char[][] board, String word) {

        if (board.length ==1 && word.length() == 1 && board[0][0] == word.charAt(0)) {
            return true;
        }
        int m = board.length;
        int n = board[0].length;
        int[][] dirs= {{-1, 0}, {1, 0}, {0,-1}, {0, 1}};
        for(int i=0;i<board.length;i++) {
            for (int j=0;j<board[0].length;j++) {
                if (board[i][j] == word.charAt(0)) {
                    if (backtrack(board, dirs, 0, i, j, m ,n, word)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean backtrack(char[][] board, int[][] dirs, int index, int row, int col, int m , int n, String word) {
        if (index == word.length()) {
            return true;
        }
        if (board[row][col] == word.charAt(index)) {
            board[row][col] = '#';
            for (int[] dir:dirs) {
                int nr = row + dir[0];
                int nc = col + dir[1];
                if (nr >=0 && nr < m && nc >=0 && nc < n) {
                    if (backtrack(board, dirs, index+1, nr, nc, m ,n, word)){
                        return true;
                    }                    
                }
            }
            board[row][col] = word.charAt(index);
        }
        return false;

    }
}
