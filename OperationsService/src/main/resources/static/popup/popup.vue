<template>
    <div class="popup flex z-10 bg-black bg-opacity-80" >
      <div class="bg-white max-w-sm mx-auto my-8">

      </div>
        <pre v-text="$attrs"/>
    </div>
</template>

<script>
    export default {
        name: 'Popup'
    };
</script>
