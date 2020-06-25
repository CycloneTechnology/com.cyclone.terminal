package com.cyclone.terminal.emulator;

/**
 * @author Phil.Baxter
 * 
 */
public final class Scroll
{
    /**
     * The direction of the scroll.
     * 
     * @author Phil.Baxter
     */
    public enum Direction
    {
        /**
         * No Scrolling is required
         */
        NONE,

        /**
         * Scroll Up one Row
         */
        UP,

        /**
         * Scroll Down one Row
         */
        DOWN;
    }

    private Direction scrollDirection;

    private int scrollAmount;

    /**
     * 
     */
    public Scroll()
    {
        scrollDirection = Direction.NONE;
    }

    /**
     * @return the number of rows to scroll
     */
    public int getScrollAmount()
    {
        return scrollAmount;
    }

    /**
     * @param a_scrollAmount
     */
    public void setScrollAmount(int a_scrollAmount)
    {
        scrollAmount = a_scrollAmount;
    }

    /**
     * 
     */
    public void increment()
    {
        scrollAmount++;
    }

    /**
     * @return the direction to scroll
     */
    public Direction getScrollDirection()
    {
        return scrollDirection;
    }

    /**
     * @param a_scrollDirection
     */
    public void setScrollDirection(Direction a_scrollDirection)
    {
        scrollDirection = a_scrollDirection;
    }
}
