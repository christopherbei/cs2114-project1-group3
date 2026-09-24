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
        if (dealerHand.size() == 2) {
            System.out.println("*");
        }
        else {
            System.out.println(dealerHand.toString());
        }
    }


    public boolean busted()
    {
        return dealerHand.totalValue() > 21;
    }
}
