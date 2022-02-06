import { WebPlugin } from '@capacitor/core';
export class BrowserWeb extends WebPlugin {
    constructor() {
        super();
        this._lastWindow = null;
    }
    // options.url, 
    async PayNow(options) {
        this._lastWindow = window.open(options.windowName || '_blank','directories=no,titlebar=no,toolbar=no,location=no,status=no');
    }
    async close() {
        return new Promise((resolve, reject) => {
            if (this._lastWindow != null) {
                this._lastWindow.close();
                this._lastWindow = null;
                resolve();
            }
            else {
                reject('No active window to close!');
            }
        });
    }
}
const Browser = new BrowserWeb();
export { Browser };
//# sourceMappingURL=web.js.map