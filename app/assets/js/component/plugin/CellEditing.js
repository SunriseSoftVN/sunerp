/**
 * Created by dungvn3000 on 4/7/14.
 */

Ext.define('sunerp.component.plugin.CellEditing', {
    extend: 'Ext.grid.plugin.CellEditing',
    onEditComplete: function() {
        this.callParent(arguments);
    }
});