// service-worker
const cacheName = 'sportnotifier-pwa-cache-v3';
const urlsToCache = [
    'favicon.png'
];

self.addEventListener('install', event => {
    event.waitUntil(
        caches.open(cacheName).then(cache => {
            return cache.addAll(urlsToCache);
        })
    );
});
