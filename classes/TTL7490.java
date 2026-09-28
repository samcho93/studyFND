class TTL7490{
    int num;
    int[] output = new int[4];
    int oldclk;
    
    TTL7490(){
        oldclk = 0;
        reset(1, 0, 0);
    }
    
    TTL7490(int R0, int R1, int R2){
        oldclk = 0;        
        reset(R0, R1, R2);
    }
    
    private void cnvt(){
        int n = num;
        
        for(int i=0; i<4; i++){
            output[i] = n % 2;
            n = (int)(n / 2);
        }
    }
    
    public void setClock(int clk){
        if(clk == 0 && oldclk == 1){
            if(++num == 10) num = 0;
            
            cnvt();
        }
        
        oldclk = clk;
    }
    
    public void reset(int R0, int R1, int R2){
        if(R0 == 1) num = 0;
        else if(R1 == 1) num = 1;
        else if(R2 == 1) num = 2;
        
        cnvt();
    }
    
    public int[] getOutput(){
        return output;
    }
    
    public void setNum(int n){
        num = n;
        cnvt();
    }
}
