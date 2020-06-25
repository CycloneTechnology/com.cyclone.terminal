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

/**
 * @author Phil.Baxter
 * 
 */
public final class SimpleDataScopeDialog implements DataScope
{
    private final Shell dialogShell;

    private final StyledText messageText;

    private final Scope tx;

    private final Scope rx;

    private final class Scope
    {
        private StyledText text;

        private final Direction direction;

        Scope(final Composite a_parent, final Direction a_direction)
        {
            direction = a_direction;

            final Composite parentComposite = new Composite(a_parent, SWT.NONE);
            parentComposite.setLayout(new GridLayout(1, true));
            parentComposite.setLayoutData(
                    new GridData(SWT.FILL, SWT.FILL, true, true));

            final Label label = new Label(parentComposite, SWT.NONE);
            label.setText(a_direction.getDescription());

            text = new StyledText(parentComposite, SWT.MULTI | SWT.BORDER
                    | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
            text.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

            final Font font = new Font(a_parent.getDisplay(), "Courier New", 8,
                    SWT.NORMAL);
            text.setFont(font);

            text.addListener(SWT.Modify, new Listener()
            {
                @Override
                public void handleEvent(Event a_e)
                {
                    text.setTopIndex(text.getLineCount() - 1);
                }
            });
        }

        void add(byte[] a_Data, int a_count)
        {
            if ((a_count > 0) && (!text.isDisposed()))
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

                        final int start = text.getCharCount();

                        text.append(sb.toString());

                        final StyleRange styleRange = new StyleRange();
                        styleRange.start = start;
                        styleRange.length = sb.length();
                        styleRange.foreground = direction.getColor();
                        text.setStyleRange(styleRange);
                    }
                });
            }
        }
    }

    /**
     * @author Phil.Baxter
     * 
     */
    public enum Direction
    {
     /**
      * 
      */
     SEND("Data Sent to Device",
             Display.getDefault().getSystemColor(SWT.COLOR_BLACK)),
     /**
      * 
      */
     RECEIVE("Data Received from Device",
             Display.getDefault().getSystemColor(SWT.COLOR_BLACK));

        private final String description;

        private final Color color;

        Direction(final String a_description, final Color a_color)
        {
            description = a_description;
            color = a_color;
        }

        /**
         * @return the description
         */
        public String getDescription()
        {
            return description;
        }

        /**
         * @return the color
         */
        public Color getColor()
        {
            return color;
        }
    }

    /**
     * @param a_parent
     */
    public SimpleDataScopeDialog(final Shell a_parent)
    {

        dialogShell = new Shell(a_parent,
                SWT.DIALOG_TRIM | SWT.MODELESS | SWT.RESIZE);
        dialogShell.setLayout(new GridLayout());
        dialogShell.setText("Terminal Data Scope");

        final Composite parentComposite = new Composite(dialogShell, SWT.NONE);
        parentComposite.setLayout(new GridLayout(2, true));
        parentComposite
                .setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        tx = new Scope(parentComposite, Direction.SEND);
        rx = new Scope(parentComposite, Direction.RECEIVE);

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
        messageText = new StyledText(parentComposite, SWT.MULTI | SWT.BORDER
                | SWT.V_SCROLL | SWT.H_SCROLL | SWT.READ_ONLY);
        messageText.setLayoutData(messageLayoutData);

        messageText.addListener(SWT.Modify, new Listener()
        {
            @Override
            public void handleEvent(Event a_e)
            {
                messageText.setTopIndex(messageText.getLineCount() - 1);
            }
        });

        dialogShell.setSize(1024, 500);
        dialogShell.open();
    }

    @Override
    public void close()
    {
        dialogShell.close();
    }

    @Override
    public void add(final byte[] a_Data, final int a_count,
            final Direction a_direction)
    {
        switch (a_direction)
        {
            case RECEIVE:
                rx.add(a_Data, a_count);
                break;
            case SEND:
                tx.add(a_Data, a_count);
                break;
            default:
                break;
        }
    }

    @Override
    public void addMessage(final String a_message)
    {
        messageText.getDisplay().asyncExec(new Runnable()
        {
            @Override
            public void run()
            {
                messageText.append(a_message);
                messageText.append("\n");
            }
        });
    }
}
