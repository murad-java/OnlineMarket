
export  default function (instance){
    return{
        getContent(link){
            return instance.get(link)
        }
    }
}