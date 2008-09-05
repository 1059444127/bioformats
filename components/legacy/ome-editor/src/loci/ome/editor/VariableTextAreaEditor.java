//
// VariableTextAreaEditor.java
//

/*
OME Metadata Editor application for exploration and editing of OME-XML and
OME-TIFF metadata. Copyright (C) 2006-@year@ Christopher Peterson.

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU Library General Public License as published by
the Free Software Foundation; either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU Library General Public License for more details.

You should have received a copy of the GNU Library General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package loci.ome.editor;

import java.awt.Component;
import java.awt.event.FocusEvent;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.TableCellEditor;

/**
 * A class that defines a textarea editor for cells defined
 * to be of type "Desc" in the template. This gives the user
 * more room in these cells to type long descriptions. When
 * clicked on, the cell expands by four times.
 *
 * <dl><dt><b>Source code:</b></dt>
 * <dd><a href="https://skyking.microscopy.wisc.edu/trac/java/browser/trunk/components/legacy/ome-editor/src/loci/ome/editor/VariableTextAreaEditor.java">Trac</a>,
 * <a href="https://skyking.microscopy.wisc.edu/svn/java/trunk/components/legacy/ome-editor/src/loci/ome/editor/VariableTextAreaEditor.java">SVN</a></dd></dl>
 *
 * @author Christopher Peterson crpeterson2 at wisc.edu
 */
public class VariableTextAreaEditor extends VariableTextEditor
  implements TableCellEditor
{

  /** Construct a new VariableTextEditor.*/
  public VariableTextAreaEditor(MetadataPane.TablePanel tp) { super(tp); }

  // -- TableCellEditor API methods --

  /**
  * The method that a table calls to ask for the editing component of
  * a particular cell. Also handles expansion of the row in this case.
  * @return The component that is this area's editor (a JTextArea).
  */
  public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column)
  {
    table.setRowHeight(row, table.getRowHeight() * 4);
    JTextArea thisText = new JTextArea(new RowDoc(row), (String) value, 1, 4);
    thisText.setLineWrap(true);
    thisText.setWrapStyleWord(true);
    thisText.setEditable(true);
    thisText.getDocument().addDocumentListener(this);
    thisText.addFocusListener(this);
    thisText.addMouseListener(this);
    JScrollPane jScr = new JScrollPane(thisText);
    return jScr;
  }

  /** Handles the shrinking of this cell when the user's done with editing.*/
  public void focusLost(FocusEvent e) {
    if (e.getSource() instanceof JTextArea) {
      JTextArea jta = (JTextArea) e.getSource();
      RowDoc rd = (RowDoc) jta.getDocument();
      tableP.table.setRowHeight(rd.row, tableP.table.getRowHeight());
      fireEditingStopped();
    }
  }

}
