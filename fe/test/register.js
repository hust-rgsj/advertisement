import puppeteer from "puppeteer";

const sleep = async (time) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve();
    }, time);
  });
};

const register = async (page, username = "test", password = "123456") => {
  await page.waitForSelector(".register");
  await sleep(1000);
  await page.click(".register");
  await sleep(1000);

  await page.waitForSelector("#username");
  await page.click("#username");
  await page.keyboard.type(username);
  await sleep(1000);

  await page.click("#password");
  await page.keyboard.type(password);
  await sleep(1000);

  await page.click("#confirm-password");
  await page.keyboard.type(password);
  await sleep(1000);

  await page.click("#submit");
  await sleep(1000);

  await page.screenshot({ path: "./result/register.png" });
};

(async () => {
  const browser = await puppeteer.launch({
    headless: false,
    defaultViewport: null,
    args: ["--start-maximized"],
  });
  const page = await browser.newPage();
  await page.goto("http://localhost:5173/");

  const username = Math.floor(Math.random() * 100000).toString();
  await register(page, username, "123456");

  await browser.close();
})();
