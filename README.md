# onlypass-capacitor-plugin

The Onlypass API provides One platform to access all payment and fintech services across Africa.

## Install

```bash
npm install onlypass-capacitor-plugin
npx cap sync
```

## Android

### Variables

This plugin will use the following project variables:
* amount:string;
*gateway:string; // (payment getways e.g paysack,flutterwave,monnify,voguepay) seperated by comma
* apikey:string;
* memo:string;

## Example

```typescript
import { Onlypass } from 'onlypass-capacitor-plugin';

const Payment = async () => {
  await Onlypass.PayNow({
    amount:"5000",
    gateway:"paysack,flutterwave,monnify,voguepay",
    memo:"Payment for shoe item",
    apikey:"onlypass-test"
  });
};
```

## API
<docgen-index>

* [`PayNow(...)`](#PayNow)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### PayNow(...)

```typescript
PayNow(options: OpenOptions) => Promise<void>
```

</docgen-api>