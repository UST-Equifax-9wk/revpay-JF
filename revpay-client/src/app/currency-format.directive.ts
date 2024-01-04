import { Directive, ElementRef, HostListener } from '@angular/core';

@Directive({
  selector: '[appCurrencyFormat]',
  standalone: true
})
export class CurrencyFormatDirective {

  constructor(private el: ElementRef) {}

  @HostListener('input', ['$event']) onInputChange(event: Event): void {
    const input = event.target as HTMLInputElement;
    let value = input.value.replace(/[^\d.-]/g, '');

    // Optional: Limit to two decimal places on input
    const match = value.match(/(\d+)(\.\d{0,2})?/);
    value = match ? match[0] : '';

    // Convert to a number and format as a currency
    const formattedValue = (parseFloat(value) || 0).toLocaleString('en-US', {
      style: 'decimal',
      maximumFractionDigits: 2,
      minimumFractionDigits: 2
    });

    // Update the input value
    this.el.nativeElement.value = formattedValue;
  }
}
