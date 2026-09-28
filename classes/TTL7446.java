class TTL7446 {
    int num;
    int[] input;
    int[] output;
    
    TTL7446(){
        input = new int[]{0,0,0,0};
        output = new int[7];
        cnvt();
    }
    
    TTL7446(int[] in){
        input = new int[4];
        output = new int[7];
        input = in;
        cnvt();
    }
    
    private void cnvt(){
        int[][] fnd = {
            {1,1,1,1,1,1,0},
            {0,1,1,0,0,0,0},
            {1,1,0,1,1,0,1},
            {1,1,1,1,0,0,1},
            {0,1,1,0,0,1,1},
            {1,0,1,1,0,1,1},
            {1,0,1,1,1,1,1},
            {1,1,1,0,0,1,0},
            {1,1,1,1,1,1,1},
            {1,1,1,1,0,1,1}
        };
        
        num = input[3] * 8 + input[2] * 4 + input[1] * 2 + input[0] * 1;
        
        output = fnd[num];
    }
    
    void setInput(int[] in){
        input = in;
        cnvt();
    }
    
    int getNum(){
        return num;
    }
    
    int[] getOutput(){
        return output;
    }
}
