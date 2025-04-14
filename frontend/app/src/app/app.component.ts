import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, FormsModule], // Added FormsModule for ngModel
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent {
  title = 'Pathfinder 2e Shared Inventory';

  // Mock data for inventory and money management
  inventory = [
    { name: 'Healing Potion', quantity: 5 },
    { name: 'Rope (50 ft)', quantity: 2 },
    { name: 'Torch', quantity: 10 },
    { name: 'Longsword', quantity: 1 }
  ];

  money = {
    gold: 100,
    silver: 50,
    copper: 200
  };

  customGold: number = 0;
  customSilver: number = 0;
  customCopper: number = 0;

  // Methods to manage inventory and money
  addItem(itemName: string, quantity: number) {
    const existingItem = this.inventory.find(item => item.name === itemName);
    if (existingItem) {
      existingItem.quantity += quantity;
    } else {
      this.inventory.push({ name: itemName, quantity });
    }
  }

  removeItem(itemName: string, quantity: number) {
    const existingItem = this.inventory.find(item => item.name === itemName);
    if (existingItem) {
      existingItem.quantity -= quantity;
      if (existingItem.quantity <= 0) {
        this.inventory = this.inventory.filter(item => item.name !== itemName);
      }
    }
  }

  // Add custom amount of money
  addCustomMoney(gold: number, silver: number, copper: number) {
    const totalCopper = this.money.gold * 100 + this.money.silver * 10 + this.money.copper;
    const newTotalCopper = totalCopper + gold * 100 + silver * 10 + copper;

    this.money.gold = Math.floor(newTotalCopper / 100);
    this.money.silver = Math.floor((newTotalCopper % 100) / 10);
    this.money.copper = newTotalCopper % 10;
  }

  // Subtract custom amount of money
  subtractCustomMoney(gold: number, silver: number, copper: number) {
    const totalCopper = this.money.gold * 100 + this.money.silver * 10 + this.money.copper;
    const newTotalCopper = totalCopper - (gold * 100 + silver * 10 + copper);

    if (newTotalCopper < 0) {
      this.money.gold = 0;
      this.money.silver = 0;
      this.money.copper = 0;
    } else {
      this.money.gold = Math.floor(newTotalCopper / 100);
      this.money.silver = Math.floor((newTotalCopper % 100) / 10);
      this.money.copper = newTotalCopper % 10;
    }
  }
}
