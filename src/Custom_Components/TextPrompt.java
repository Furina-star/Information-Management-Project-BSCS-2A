package Custom_Components;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;
import javax.swing.text.*;

/**
 * The TextPrompt class will display a prompt over top of a text component when
 * the Document of the text component is empty. The Show property determines
 * when the prompt is displayed.
 *
 * The prompt can be positioned to the left, center or right of the text
 * component.
 */
public class TextPrompt extends JLabel implements FocusListener, DocumentListener
{
	public enum Show
	{
		ALWAYS,
		FOCUS_GAINED,
		FOCUS_LOST;
	}

	private JTextComponent component;
	private Document document;

	private Show show;
	private boolean showPromptOnce;
	private int focusLost;

	public TextPrompt(String text, JTextComponent component)
	{
		this(text, component, Show.ALWAYS);
	}

	public TextPrompt(String text, JTextComponent component, Show show)
	{
		this.component = component;
		setShow( show );
		document = component.getDocument();

		setText( text );
		setFont( component.getFont() );
		setForeground( new java.awt.Color(153, 153, 153) );
		setBorder( new EmptyBorder(component.getInsets()) );
		setHorizontalAlignment(JLabel.LEADING);

		component.addFocusListener( this );
		document.addDocumentListener( this );

		component.setLayout( new BorderLayout() );
		component.add( this );
		checkForAndShowPrompt();
	}

	/**
	 * Convenience method to change the alpha value of the current foreground
	 * Color to make the prompt text faded.
	 *
	 * @param alpha value in the range of 0 - 1.0.
	 */
	public void changeAlpha(float alpha)
	{
		changeAlpha( (int)(alpha * 255) );
	}

	/**
	 * Convenience method to change the alpha value of the current foreground
	 * Color to make the prompt text faded.
	 *
	 * @param alpha value in the range of 0 - 255.
	 */
	public void changeAlpha(int alpha)
	{
		alpha = alpha > 255 ? 255 : alpha < 0 ? 0 : alpha;

		Color foreground = getForeground();
		int red = foreground.getRed();
		int green = foreground.getGreen();
		int blue = foreground.getBlue();

		Color withAlpha = new Color(red, green, blue, alpha);
		super.setForeground( withAlpha );
	}

	/**
	 * Convenience method to change the style of the prompt text. The style
	 * values are found in the Font class. Common values might be:
	 * Font.BOLD, Font.ITALIC, Font.BOLD + Font.ITALIC.
	 *
	 * @param style value representing the new style of the Font.
	 */
	public void changeStyle(int style)
	{
		setFont( getFont().deriveFont( style ) );
	}

	/**
	 * Get the Show property
	 *
	 * @return the Show property.
	 */
	public Show getShow()
	{
		return show;
	}

	/**
	 * Set the prompt Show property to control when the prompt is shown.
	 * Valid values are:
	 *
	 * Show.ALWAYS (default) - always show the prompt when the text component is empty
	 * Show.FOCUS_GAINED - show the prompt when the text component gains focus
	 * Show.FOCUS_LOST - show the prompt when the text component loses focus
	 *
	 * @param show a valid Show enum
	 */
	public void setShow(Show show)
	{
		this.show = show;
	}

	/**
	 * Get the showPromptOnce property
	 *
	 * @return the showPromptOnce property.
	 */
	public boolean getShowPromptOnce()
	{
		return showPromptOnce;
	}

	/**
	 * Show the prompt once. Once the component has gained/lost focus
	 * once, the prompt will not be shown again.
	 *
	 * @param showPromptOnce  when true the prompt will only be shown once,
	 * otherwise it will be shown repeatedly.
	 */
	public void setShowPromptOnce(boolean showPromptOnce)
	{
		this.showPromptOnce = showPromptOnce;
	}

	/**
	 *	Check whether the prompt should be visible or not. The visibility
	 * will change on updates to the Document and on focus changes.
	 */
	private void checkForAndShowPrompt()
	{
		//  Text has been entered, remove the prompt

		if (document.getLength() > 0)
		{
			setVisible( false );
			return;
		}

		//  Prompt has already been shown once, remove it

		if (showPromptOnce && focusLost > 0)
		{
			setVisible(false);
			return;
		}

		//  Check the Show property and component focus to determine if the
		//  prompt should be displayed.

        if (component.hasFocus())
        {
        	if (show == Show.ALWAYS
        	||  show ==	Show.FOCUS_GAINED)
				setVisible( true );
        	else
        		setVisible( false );
        }
        else
        {
        	if (show == Show.ALWAYS
        	||  show ==	Show.FOCUS_LOST)
				setVisible( true );
        	else
        		setVisible( false );
        }
	}

//  Implement FocusListener

	public void focusGained(FocusEvent e)
	{
		checkForAndShowPrompt();
	}

	public void focusLost(FocusEvent e)
	{
		focusLost++;
		checkForAndShowPrompt();
	}

//  Implement DocumentListener

	public void insertUpdate(DocumentEvent e)
	{
		checkForAndShowPrompt();
	}

	public void removeUpdate(DocumentEvent e)
	{
		checkForAndShowPrompt();
	}

	public void changedUpdate(DocumentEvent e) {}
}
