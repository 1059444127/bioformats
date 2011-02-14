//
// VariableTextFieldEditor.java
//

/*
OME Metadata Editor application for exploration and editing of OME-XML and
OME-TIFF metadata. Copyright (C) 2006-@year@ Christopher Peterson.

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU Library General Public License as published by
the Free Software Foundation; either version 2 of the License, or
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

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.TableCellEditor;

/**
 * A class that handles editing of a cell that is defined in
 * the template as having no Type attribute, e.g. it is neither
 * of type "Ref" or type "Desc". Creates a JTextField to edit
 * this cell instead of something fancy.
 *
 * <dl><dt><b>Source code:</b></dt>
 * <dd><a href="http://trac.openmicroscopy.org.uk/ome/browser/bioformats.git/components/legacy/ome-editor/src/loci/ome/editor/VariableTextFieldEditor.java">Trac</a>,
 * <a href="http://git.openmicroscopy.org/?p=bioformats.git;a=blob;f=components/legacy/ome-editor/src/loci/ome/editor/VariableTextFieldEditor.java;hb=HEAD">Gitweb</a></dd></dl>
 *
 * @author Christopher Peterson crpeterson2 at wisc.edu
 */
public class VariableTextFieldEditor extends VariableTextEditor
  implements TableCellEditor
{

  /** Construct a new VariableTextFieldEditor.*/
  public VariableTextFieldEditor(MetadataPane.TablePanel tp) {
    super(tp);
  }

  // -- TableCellEditor API methods --

  /**
  * The method a table calls to get the editing component for a
  * particular cell.
  */
  public Component getTableCellEditorComponent(JTable table, Object value,
    boolean isSelected, int row, int column)
  {
    JTextField text = new JTextField(new RowDoc(row), (String) value, 1);
    text.getDocument().addDocumentListener(this);
    text.addFocusListener(this);
    text.addMouseListener(this);
    text.addActionListener(this);
    return text;
  }
}
