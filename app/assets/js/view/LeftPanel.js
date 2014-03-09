Ext.define('sunerp.view.LeftPanel', {
    extend: 'Ext.tree.Panel',
    xtype: 'navigation',
    title: 'Menu',
    rootVisible: false,
    lines: false,
    useArrows: true,
    inject: ['navigationStore'],
    config: {
        navigationStore: null
    },

    initComponent: function() {
        this.store = this.getNavigationStore();
        this.callParent(arguments);
    }
});