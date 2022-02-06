import { registerPlugin } from '@capacitor/core';
const Onlypass = registerPlugin('Onlypass', {
    web: () => import('./web').then(m => new m.BrowserWeb()),
});
export * from './definitions';
export { Onlypass };
//# sourceMappingURL=index.js.map