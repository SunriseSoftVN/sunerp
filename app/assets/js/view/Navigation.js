Ext.define('sunerp.view.Navigation', {
    extend: 'Ext.tree.Panel',
    xtype: 'navigation',
    title: 'Menu',
    rootVisible: false,
    lines: false,
    useArrows: true,
    store: Ext.create('sunerp.store.Navigations')
});