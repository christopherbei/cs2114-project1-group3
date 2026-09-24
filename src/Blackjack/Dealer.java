import java.util.*;

public class Dealer
{
    private Hand dealerHand;

    public Dealer()
    {
        dealerHand = new Hand();
    }


    public void addCard(Card card)
    {
        dealerHand.addCard(card);
        System.out.println(this.toString());
    }


    public boolean busted()
    {
        return dealerHand.totalValue() > 21;
    }
    
    
    public String toString() {
        String result = "Dealer:" + "/n";
        if (dealerHand.size() ==2) {
            result += dealerHand.getCards().get(0) + "/n";
            result += "*";
        }
        else {
            result += dealerHand.toString();
        }
        return result;
    }
}
