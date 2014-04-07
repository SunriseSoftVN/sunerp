/**
 * Created by dungvn3000 on 3/14/14.
 * The model automatic update mapping field when set associations table.
 */

Ext.define('sunerp.model.BaseModel', {
    extend: 'Ext.data.Model',
    setAssociationsValue: function (fieldName, newValue) {
        var me = this;
        if (newValue != null && Ext.isObject(newValue)) {
            for (var key in newValue) {
                me.set(fieldName + "." + key, newValue[key]);
            }
            me.set(fieldName + 'Id', newValue.id);
        }
    },
    set: function(fieldName, newValue) {
        var index = fieldName.indexOf('.');
        if(index > 0) {
            var table = fieldName.substring(0, index);
            var field = fieldName.substring(index + 1, fieldName.length);
            var obj = this.get(table);
            if(obj != null) {
                obj[field] = newValue;
            }
        }
        this.callParent(arguments)
    }
});