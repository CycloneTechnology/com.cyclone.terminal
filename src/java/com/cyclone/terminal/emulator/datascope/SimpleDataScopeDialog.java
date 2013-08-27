package com.cyclone.terminal.emulator.datascope;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyleRange;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class SimpleDataScopeDialog implements DataScope
{
    private final Shell m_dialogShell;

    private final StyledText m_messageText;

    private final Scope m_tx;

    private final Scope m_rx;

    private final class Scope
    {
        private StyledText m_text;

        private final Direction m_direction;

        public Scope(final Composite a_parent, final Direction a_direction)
        {
            m_direction = a_direction;

            final Composite parentComposite = new Composite(a_parent, SWT.NONE);
            parentComposite.setLayout(new GridLayout(1, true));
            parentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL,
                    true, true));

            final Label label = new Label(parentComposite, SWT.NONE);
            label.setText(a_direction.getDescription());

            m_text = new StyledText(parentComposite, SWT.MULTI | SWT.BORDER
                    | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
            m_text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

            final Font font = new Font(a_parent.getDisplay(), "Courier New", 8,
                    SWT.NORMAL);
            m_text.setFont(font);

            m_text.addListener(SWT.Modify, new Listener()
            {
                /**
                 * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
                 */
                @Override
                public void handleEvent(Event a_e)
                {
                    m_text.setTopIndex(m_text.getLineCount() - 1);
                }
            });
        }

        void add(byte[] a_Data, int a_count)
        {
            if ((a_count > 0) && (!m_text.isDisposed()))
            {
                final StringBuilder sb = new StringBuilder();

                for (int index = 0; index < a_count; index++)
                {
                    if (a_Data[index] < ASCII_CODES.length)
                    {
                        sb.append(ASCII_CODES[a_Data[index] & 0xff]);
                        if (a_Data[index] == 13)
                        {
                            sb.append("\r");
                        }
                        if (a_Data[index] == 10)
                        {
                            sb.append("\n");
                        }
                    }
                    else
                    {
                        sb.append("***");
                    }
                }

                Display.getCurrent().asyncExec(new Runnable()
                {
                    @Override
                    public void run()
                    {

                        final int start = m_text.getCharCount();

                        m_text.append(sb.toString());

                        final StyleRange styleRange = new StyleRange();
                        styleRange.start = start;
                        styleRange.length = sb.length();
                        styleRange.foreground = m_direction.getColor();
                        m_text.setStyleRange(styleRange);
                    }
                });
            }
        }
    }

    public enum Direction
    {
        SEND("Data Sent to Device", Display.getDefault().getSystemColor(
                SWT.COLOR_BLACK)),
        RECEIVE("Data Received from Device", Display.getDefault()
                .getSystemColor(SWT.COLOR_BLACK));

        private final String m_description;

        private final Color m_color;

        private Direction(final String a_description, final Color a_color)
        {
            m_description = a_description;
            m_color = a_color;
        }

        /**
         * @return the description
         */
        public String getDescription()
        {
            return m_description;
        }

        /**
         * @return the color
         */
        public Color getColor()
        {
            return m_color;
        }
    }

    public SimpleDataScopeDialog(final Shell a_parent)
    {

        m_dialogShell = new Shell(a_parent, SWT.DIALOG_TRIM | SWT.MODELESS
                | SWT.RESIZE);
        m_dialogShell.setLayout(new GridLayout());
        m_dialogShell.setText("Terminal Data Scope");

        final Composite parentComposite = new Composite(m_dialogShell, SWT.NONE);
        parentComposite.setLayout(new GridLayout(2, true));
        parentComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true,
                true));

        m_tx = new Scope(parentComposite, Direction.SEND);
        m_rx = new Scope(parentComposite, Direction.RECEIVE);

        final GridData labelLayoutData = new GridData(SWT.FILL, SWT.FILL, true,
                false);
        labelLayoutData.grabExcessHorizontalSpace = true;
        labelLayoutData.horizontalSpan = 2;
        final Label label = new Label(parentComposite, SWT.NONE);
        label.setLayoutData(labelLayoutData);
        label.setText("Messages");

        final GridData messageLayoutData = new GridData(SWT.FILL, SWT.FILL,
                true, true);
        messageLayoutData.grabExcessHorizontalSpace = true;
        messageLayoutData.horizontalSpan = 2;
        m_messageText = new StyledText(parentComposite, SWT.MULTI | SWT.BORDER
                | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
        m_messageText.setLayoutData(messageLayoutData);

        m_messageText.addListener(SWT.Modify, new Listener()
        {
            /**
             * @see org.eclipse.swt.widgets.Listener#handleEvent(org.eclipse.swt.widgets.Event)
             */
            @Override
            public void handleEvent(Event a_e)
            {
                m_messageText.setTopIndex(m_messageText.getLineCount() - 1);
            }
        });

        m_dialogShell.setSize(1024, 500);
        m_dialogShell.open();
    }

    /**
     * @see com.cyclone.terminal.emulator.datascope.DataScope#close()
     */
    @Override
    public void close()
    {
        m_dialogShell.close();
    }

    /**
     * @see com.cyclone.terminal.emulator.datascope.DataScope#add(byte[],
     *      int,
     *      com.cyclone.terminal.emulator.datascope.SimpleDataScopeDialog.Direction)
     */
    @Override
    public void add(final byte[] a_Data, final int a_count,
            final Direction a_direction)
    {
        switch (a_direction)
        {
            case RECEIVE:
                m_rx.add(a_Data, a_count);
                break;
            case SEND:
                m_tx.add(a_Data, a_count);
                break;
            default:
                break;
        }
    }

    /**
     * @see com.cyclone.terminal.emulator.datascope.DataScope#addMessage(java.lang.String)
     */
    @Override
    public void addMessage(final String a_message)
    {
        m_messageText.getDisplay().asyncExec(new Runnable()
        {
            /**
             * @see java.lang.Runnable#run()
             */
            @Override
            public void run()
            {
                m_messageText.append(a_message);
                m_messageText.append("\n");
            }
        });
    }
}
