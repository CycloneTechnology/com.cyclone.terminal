/**
 * 
 */
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

    private Direction m_scrollDirection;

    private int m_scrollAmount;

    /**
     * 
     */
    public Scroll()
    {
        m_scrollDirection = Direction.NONE;
    }

    /**
     * @return the number of rows to scroll
     */
    public int getScrollAmount()
    {
        return m_scrollAmount;
    }

    /**
     * @param a_scrollAmount
     */
    public void setScrollAmount(int a_scrollAmount)
    {
        m_scrollAmount = a_scrollAmount;
    }

    /**
     * 
     */
    public void increment()
    {
        m_scrollAmount++;
    }

    /**
     * @return the direction to scroll
     */
    public Direction getScrollDirection()
    {
        return m_scrollDirection;
    }

    /**
     * @param a_scrollDirection
     */
    public void setScrollDirection(Direction a_scrollDirection)
    {
        m_scrollDirection = a_scrollDirection;
    }
}
