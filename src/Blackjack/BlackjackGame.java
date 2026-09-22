
public class BlackjackGame {
    private int currentBid;
    
    Deck deck;
    Player player;
    Dealer dealer;
    
    public BlackjackGame() {
        currentBid = 1000;
        
        deck = new Deck();
        player = new Player();
        dealer = new Dealer();
    }
    
    public void runCommand(String action) {
        
    }
    
    public void pauseConsole(double duration) {
        
    }
    
    public String handleInput() {
        
    }
}
