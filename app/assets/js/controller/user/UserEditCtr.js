Ext.define('sunerp.controller.user.UserEditCtr', {
    extend: 'Deft.mvc.ViewController',
    inject: ['userStore'],
    config: {
        user: null,
        userStore: null
    },
    control: {
        form: {
            selector: 'form'
        },
        saveBtn: {
            selector: 'button[action=save]',
            listeners: {
                click: 'doUpdate'
            }
        }
    },
    init: function() {
        this.setUser(this.getView().getUser());
        this.getForm().loadRecord(this.getUser());
        this.callParent(arguments);
    },
    doUpdate: function() {
        var view = this.getView(),
            form = this.getForm(),
            record = form.getRecord(),
            values = form.getValues(),
            me = this;
        if (form.isValid()) {
            if (record != null) {
                //update record
                record.set(values);
            } else {
                //add new record
                me.getUserStore().add(values);
            }

            if(record == null || record.dirty) {
                // synchronize the store after editing the record
                me.getUserStore().sync({
                    success: function () {
                        view.close();
                        me.getUserStore().reload();
                    }
                });
            } else {
                view.close();
            }
        }
    }
});