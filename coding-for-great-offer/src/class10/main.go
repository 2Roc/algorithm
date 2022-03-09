package main

import (
	"fmt"
	"strings"
	"unsafe"
)

func main() {
	str := 32.1
	str1 := 25.5
	string := "175.123.1121.2"
	//精度丢失
	if str-str == 6.600000000000001 {
		fmt.Printf("%d,%v\n", unsafe.Sizeof(str), str-str1)
	}
	split := strings.Split(string, ".")
	split = append(split, "key")
	//for i, s := range split {
	//	fmt.Printf("%v,%v\n", s, i)
	//}

	/**
	对p变量&操作得到对应的内存地址（指针变量），指针变量的值是指针地址
	s = &p  s存放p的指针变量&p，类型为指针类型，用*T表示，*表示指针
	对指针类型数据取值用*，可以获得它存放的指针地址对应的值；
	*/
	p := 10

	s := 100
	fmt.Printf("%v,%T,%v,%v\n", &p, s, s, &s)
	s,p =p, s

		fmt.Printf("%v,%T,%v,%v\n", &p, s, s, &s)
	p = 100
	fmt.Printf("%v,%T,%p,%v,%T\n", p, s, s, s, s)

	fmt.Printf("%c %d %T\n", string[2], unsafe.Sizeof(string[2]), string[2])
}
