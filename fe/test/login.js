import puppeteer from "puppeteer";

const sleep = async (time) => {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      resolve();
    }, time);
  });
};

const login = async (page, username = "000", password = "000") => {
  await sleep(1000);
  await page.waitForSelector("#username");
  await page.click("#username");
  await page.keyboard.type(username);
  await sleep(1000);

  await page.click("#password");
  await page.keyboard.type(password);
  await sleep(1000);

  await page.click("#submit");
  await sleep(1000);
  await page.screenshot({ path: "./result/login.png" });
};

(async () => {
  const browser = await puppeteer.launch({
    headless: false,
    defaultViewport: null,
    args: ["--start-maximized"],
  });
  const page = await browser.newPage();
  await page.goto("http://localhost:5173/");

  await login(page);

  await browser.close();
})();
